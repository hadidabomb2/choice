# Java Design Templates

This template package includes a comprehensive collection of 23 Gang of Four design patterns plus real-world case studies, all implemented in pure Java using only standard library features.

## What's Included

- **Design Patterns**: Comprehensive examples of all 23 classic GoF patterns (Creational, Structural, and Behavioral)
- **Case Studies**: Real-world applications including rate limiting, real-time stock tracking, payment processing, and notification services
- **Entry Point**: `designtemplates/Main.java` — runnable demo of all patterns
- **Standard Library Only**: No external dependencies

## Quick Start

```bash
javac designtemplates/*.java designtemplates/*/*.java
java designtemplates.Main
```

## Structure

- `designtemplates/` — All patterns organized by type
- `designtemplates/Main.java` — Main entry point with demonstrations
- Pattern folders contain concrete implementations

## Style Notes

- Each pattern demo is kept small and focused with descriptive method names
- Named constants replace numeric literals for clarity
- Pure Java with no external frameworks
