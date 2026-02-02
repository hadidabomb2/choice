# Templates

This document is the **canonical reference** for template structure, versioning, and contribution.

---

## Folder Layout

```
templates/<language>/<version>/
```

Example:

```
templates/python/v1/
templates/java/v1/
templates/javascript/v1/
```

---

## Required Files (per template)

Each template folder must include:

- `template.json` — metadata
- `designtemplates/main.py`, `designtemplates/Main.java`, or `designtemplates/main.js` — entry file
- `README.md` — usage documentation
- Design template package organized under `designtemplates/`

---

## What is a Design Template?

A design template package includes:
- **23 Gang of Four patterns**: All classic creational, structural, and behavioral patterns
- **Real-world case studies**: Practical applications demonstrating pattern usage
- **Language-native implementation**: Using only standard library features
- **Single entry point**: Main file demonstrating all patterns

---

## Style Guidelines

- Keep entry files focused with small, named example functions
- Replace numeric literals with named constants
- Organize patterns in subdirectories by category
- Document each pattern with clear, concise comments

---

## template.json (example)

```json
{
  "name": "python",
  "version": "v1",
  "description": "Design templates package with 23 GoF patterns and real-world case studies",
  "entry": "designtemplates/main.py",
  "tools": []
}
```

---

## Available Templates

| Language | Version | Status |
|----------|---------|--------|
| Java | v1 | ✓ Complete |
| JavaScript | v1 | ✓ Complete |
| Python | v1 | ✓ Complete |


### Notes
- The `tools` array is currently **metadata only**.
- Future versions may use `tools` for automatic setup or validation.

---

## templates/index.json

This file defines:

- available languages
- available versions
- runtime requirements
- which version is “latest”

Example:

```json
{
  "python": {
    "latest": "v1",
    "versions": ["v1"],
    "runtime": "python",
    "runtimeVersion": "3.13",
    "description": "Python Decorator + Simple Factory + Factory Method + Abstract Factory template"
  }
}
```

---

## Versioning Rules

- Start at **v1**
- Increment to **v2**, **v3** for breaking changes
- `latest` controls which template is default for `choice init`

---

## Runtime Requirements

Choice does **not** install runtimes. Users must have:

- Python 3.13+
- Java 21+
- Node.js 25+

---

## Contribution Guidelines

- Keep templates minimal and documented
- Update `templates/index.json` when adding new templates
- Follow existing naming conventions