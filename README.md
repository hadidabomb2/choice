# Choice

Choice is a consumer‑first CLI that installs language templates using simple flags.

## Install (local dev)
```bash
npm install
npm run build
npm link
```

## Usage
```bash
choice list
choice list --lang python
choice init --lang python --name myapp
choice init --lang javascript --template v1
choice plugins
```

## Template Variables
Templates can include placeholders:

```
{{projectName}}
```

These are automatically replaced during `choice init`.

## Plugin Registry
The file `plugins/registry.json` is a simple extensible registry that lists
available plugins. This is a placeholder for future plugin management.

## Tests
```bash
npm test
```

## Notes
- The `tools` array in `template.json` is currently metadata only.
- Runtime versions required are declared in `templates/index.json`.