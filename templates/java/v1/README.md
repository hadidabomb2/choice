# Java Design Templates

This template package includes a comprehensive collection of 23 Gang of Four design patterns plus real-world case studies, all implemented in pure Java using only standard library features.

## What's Included

- **Design Patterns**: Comprehensive examples of all 23 classic GoF patterns (Creational, Structural, and Behavioral)
- **Pattern Entry**: `designtemplates/Main.java` — runnable demo of all patterns
- **Case Studies**: Integrated finance application demonstrating real-world architecture
- **Case Studies Entry**: `financecasestudies/Main.java` — real-time stock tracking, payments, rate limiting, notifications
- **Standard Library Only**: No external dependencies

## Quick Start

**Compile all sources:**
```bash
javac designtemplates/*.java designtemplates/*/*.java financecasestudies/*.java financecasestudies/*/*.java
```

**Run design patterns demo:**
```bash
java designtemplates.Main
```

**Run integrated finance application:**
```bash
java financecasestudies.Main
```

## Structure

- `designtemplates/` — All 23 patterns organized by category
  - `Main.java` — Pattern demonstrations
  - Pattern folders (abstractfactory, adapter, bridge, etc.)
- `financecasestudies/` — Real-world finance application
  - `Main.java` — Integrated application orchestrator
  - `notificationservice/` — Multi-channel notification system with retry logic
  - `paymentprocessing/` — Idempotent payment ledger
  - `ratelimiter/` — Token bucket rate limiting
  - `realtimestock/` — Real-time portfolio tracking

## Style Notes

- Each pattern demo is kept small and focused with descriptive method names
- Named constants replace numeric literals for clarity
- Pure Java with no external frameworks
- Case studies demonstrate practical pattern combinations in production scenarios
