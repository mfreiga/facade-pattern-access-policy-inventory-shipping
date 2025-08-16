# E-Commerce Order Flow — Facade Pattern (Java, Spring Boot)

A Spring Boot project that demonstrates the **Facade Pattern** by simplifying access to three subsystems:
- **Access Policy**: validates the user’s role.
- **Inventory**: checks and reserves product stock.
- **Shipping**: prepares shipment information.

The `OrderFacade` coordinates all three, exposing a single clean entry point for placing an order.

## Overview
- **Facade**: `OrderFacade` handles role check → inventory check → shipping confirmation.
- **Subsystems**:
  - `AccessPolicyService`: validates user access.
  - `InventoryService`: checks product quantity and reserves stock.
  - `ShippingService`: generates shipping confirmation for the order.
- **Controller**: REST endpoints that forward requests only to the facade.

This mirrors real-world design where external clients should not deal with multiple subsystems directly.

## Tech Stack
- Java 17
- Spring Boot (Web, REST)
- Gradle
- JUnit 5

## Architecture and Key Decisions
- **Facade Pattern**: hides subsystem complexity behind `OrderFacade`.
- **Spring DI**: all services injected into the facade.
- **REST API**: clients hit one endpoint (`/order`) instead of three separate ones.
- **Error handling**: if access or inventory checks fail, the facade stops the workflow.

## Features
- Validate user roles before placing an order.
- Check and reserve stock in inventory.
- Generate shipping details.
- Expose a single REST endpoint that drives the entire workflow.

## Getting Started

### Prerequisites
- Java 17
- Gradle wrapper (`./gradlew`)

### Build
```bash
./gradlew clean build