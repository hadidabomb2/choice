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
- `designtemplates/main.py`, `designtemplates/Main.java`, or `designtemplates/main.js` — patterns entry file
- `README.md` — usage documentation
- Design template package organized under `designtemplates/`
- Optional: `financecasestudies/Main.java` for Java (real-world applications)

---

## What is a Design Template?

A design template package includes:
- **23 Gang of Four patterns**: All classic creational, structural, and behavioral patterns
- **Real-world case studies**: Practical applications demonstrating pattern usage (Java only)
- **Language-native implementation**: Using only standard library features
- **Dual entry points**: Patterns demo + optional case studies application

---

## Style Guidelines

- Keep entry files focused with small, named example functions
- Replace numeric literals with named constants
- Organize patterns in subdirectories by category
- Document each pattern with clear, concise comments
- For case studies, demonstrate practical pattern combinations

---

## template.json Structure

```json
{
  "name": "java",
  "version": "v1",
  "description": "Design templates package with 23 GoF patterns and real-world case studies",
  "patterns": "designtemplates/Main.java",
  "casestudies": "financecasestudies/Main.java",
  "tools": []
}
```

**Keys:**
- `name` — Language identifier
- `version` — Template version
- `description` — Package overview
- `patterns` — Entry point for design patterns demo
- `casestudies` — Entry point for real-world applications (Java only)
- `tools` — Reserved for future tooling

---

## Available Templates

| Language | Version | Status | Patterns | Case Studies |
|----------|---------|--------|----------|---------------|
| Java | v1 | ✓ Complete | ✓ | ✓ |
| JavaScript | v1 | ✓ Complete | ✓ | — |
| Python | v1 | ✓ Complete | ✓ | — |

---

### Notes
- The `tools` array is currently **metadata only**.
- Case studies currently available for Java only (financecasestudies/).
- Future versions may expand case studies to other languages.

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