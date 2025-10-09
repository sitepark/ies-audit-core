# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

The **IES Audit Core Package** is a domain-agnostic audit logging and undo mechanism built using Clean Architecture principles. It records domain-relevant changes (create/update/delete actions) and supports reverting those changes by delegating to the owning bounded context.

This is a Java 21 Maven project using the Java Platform Module System (JPMS).

## Build Commands

### Core Build and Test
```bash
# Compile the project
mvn clean compile

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=FilterTest

# Run a single test method
mvn test -Dtest=FilterTest#testId

# Full verification (tests + code quality checks)
mvn clean verify

# Generate test coverage report
mvn jacoco:report
# View report at: target/site/jacoco/index.html
```

### Code Quality

```bash
# Apply code formatting (Google Java Style)
mvn spotless:apply

# Check formatting
mvn spotless:check

# Run SpotBugs static analysis
mvn spotbugs:check

# Run PMD checks
mvn pmd:check

# Run all quality checks (included in verify phase)
mvn verify
```

### Package and Deploy

```bash
# Create JAR files
mvn package

# Deploy snapshot (main branch only)
mvn deploy

# Release (via GitHub Actions workflow)
# Trigger via GitHub UI or: gh workflow run create-release.yml
```

## Architecture

### Clean Architecture Layers

This project follows hexagonal/clean architecture with clear separation:

1. **Domain** (`domain/`): Core entities and exceptions
   - `AuditLog` entity represents a single audit entry with metadata
   - Domain exceptions like `RevertFailedException`

2. **Use Cases** (`usecase/`): Application business logic
   - `SearchAuditLogs`: Query audit logs with filters/sorting/pagination
   - `GetAllAuditLogs`: Retrieve audit logs with simple filters
   - `RevertActions`: Undo recorded changes via delegation

3. **Ports** (`port/`): Interfaces for external dependencies
   - `AuditLogRepository`: Persistence abstraction
   - `AccessControl`: Security checks
   - `ReversalHandlerRegistry`: Delegates reversal to domain modules

4. **Service** (`service/`): Internal orchestration
   - `RevertService`: Coordinates reversal by looking up handlers

### Query System

The query subsystem (`usecase/query/`) provides flexible searching:

- **Filters** (`filter/`): 21+ filter types including `Id`, `EntityType`, `DateRange`, `Action`, `UserId`, plus logical operators (`And`, `Or`, `Not`)
- **Sorting** (`sort/`): Sort criteria with direction (ASC/DESC)
- **Pagination** (`limit/`): Offset-based pagination via `OffsetLimit`
- **Query**: Combines filters, sorting, and pagination

Filters use static factory methods from the `Filter` interface (e.g., `Filter.entityId("123")`, `Filter.and(...)`).

### Jackson Integration

Custom Jackson deserialization for polymorphic query filters:
- `DatabindModule`: Registers custom deserializers
- `UniquePropertyPolymorphicDeserializer`: Base class for polymorphic deserialization
- `FilterDeserializer`: Deserializes `Filter` implementations based on JSON properties

Register `DatabindModule` with `ObjectMapper` to enable JSON serialization of queries.

### Reversal/Undo Mechanism

Audit logs can be reverted:
1. `RevertActions` use case receives audit log IDs
2. Groups multiple reverts into a batch (creates `REVERT_BATCH` parent log)
3. `RevertService` looks up appropriate `ReverseActionHandler` from registry (via `entityType`)
4. Handler (implemented by owning domain module) performs the actual reversal
5. New audit log entry records the revert action

The core never implements domain-specific reversal logic—it only orchestrates.

## Dependency Integration

### Shared Kernel

Depends on `ies-shared-kernel` (version 1.0.0-SNAPSHOT) which provides:
- `ReverseActionHandler`: Interface for domain modules to implement undo
- `AuditLogService`: Service for creating audit log entries
- `RevertRequest`: Request object for reversal operations
- `AccessDeniedException`: Security exception

### Injection

Uses Jakarta Inject (`@Inject`) for dependency injection. Use cases and services have constructor-based injection.

## Module Exports

The module (`module-info.java`) exports:
- Domain entities and exceptions
- All use case classes
- Query components (filters, sorting, limits)
- Port interfaces

This allows adapter modules to implement ports and invoke use cases while keeping internal services private.

## Testing Notes

- Test classes follow naming convention: `<ClassName>Test`
- Tests use JUnit 5 with static imports for assertions
- Common test libraries: Mockito for mocking, EqualsVerifier for equals/hashCode, Hamcrest for matchers
- The Surefire plugin uses a tree reporter for test output
