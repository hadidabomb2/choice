# src/

This folder contains the TypeScript source for the Choice CLI.

## Structure
- `cli.ts` — command entrypoint
- `commands/` — CLI commands (init, list, plugins)
- `utils/` — filesystem helpers, template utilities
- `types.ts` — shared types
- `tests/` — node:test coverage

## Entry Flow
`cli.ts` registers commands → commands call utils → templates copied/processed.

## Template Style
See [templates/README.md](templates/README.md) for template readability guidelines.