[![codecov](https://codecov.io/gh/sitepark/ies-userrepository-core/graph/badge.svg?token=eRLHR95gFb)](https://codecov.io/gh/sitepark/ies-userrepository-core)
[![Known Vulnerabilities](https://snyk.io/test/github/sitepark/ies-userrepository-core/badge.svg)](https://snyk.io/test/github/sitepark/ies-userrepository-core/)
# Audit Core Package

The **Audit Core Package** provides a centralized mechanism for recording and reversing changes across bounded contexts. It is designed as an independent core module following Clean Architecture principles and is suitable for use in modular or modulithic systems.

### What is "Audit" in this context?

In this system, **auditing** means capturing domain-relevant actions such as creating, updating, deleting, or linking entities — including metadata like who performed the change and when. Unlike simple logging, these audit entries are structured and actionable: they can be **queried**, **inspected**, and in many cases, **undone**.

### Key Features

- Records create/update/delete actions across domain modules (e.g. users, roles, content)
- Groups related changes via `changeSet`/`batch` identifiers
- Supports undo operations by delegating reversal to the owning bounded context
- Designed to be framework-agnostic and easily testable
- Built for integration with multiple domain cores via shared contracts

### Intended Usage

Domain modules such as `userrepository` or `contentrepository` can emit audit entries via a defined port.  
They may also implement `ReversibleAuditHandler` interfaces to support undo functionality for selected actions.  
The audit core does **not** contain any domain-specific logic — it only stores, routes, and orchestrates undo requests.

