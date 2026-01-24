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
- entry files (e.g. `app/main.py`, `app/Main.java`, `app/index.js`)
- optional `README.md` for usage notes

---

## Style Guidelines

- Keep entry files focused with small, named example functions.
- Replace numeric literals in examples with named constants.

---

## template.json (example)

```json
{
  "name": "python",
  "version": "v1",
  "description": "Python Decorator + Simple Factory + Factory Method + Abstract Factory + Strategy + Facade + Flyweight + Proxy + Composite + Bridge + Adapter + Builder + Prototype + Singleton + Chain of Responsibility + Command + Iterator + Mediator + Memento + Observer + State + Template Method + Visitor template",
  "entry": "app/main.py",
  "tools": []
}
```

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