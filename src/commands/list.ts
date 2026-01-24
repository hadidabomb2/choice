import { Command } from "commander";
import { readTemplateIndex } from "../utils/templateIndex.js";

type ListOptions = {
  lang?: string;
  json?: boolean;
};

export function registerListCommand(program: Command) {
  program
    .command("list")
    .description("List available templates")
    .option("--lang <language>", "Filter by language")
    .option("--json", "Output raw JSON")
    .action(async (options: ListOptions) => {
      const index = await readTemplateIndex();

      if (options.json) {
        console.log(JSON.stringify(index, null, 2));
        return;
      }

      if (options.lang) {
        const lang = options.lang.toLowerCase();
        const info = index[lang];
        if (!info) {
          throw new Error(`Unknown language "${lang}".`);
        }

        console.log(`${lang} (latest: ${info.latest})`);
        console.log(`  versions: ${info.versions.join(", ")}`);
        console.log(`  runtime: ${info.runtime} ${info.runtimeVersion}`);
        console.log(`  description: ${info.description}`);
        return;
      }

      for (const [lang, info] of Object.entries(index)) {
        console.log(`${lang} (latest: ${info.latest})`);
        console.log(`  versions: ${info.versions.join(", ")}`);
        console.log(`  runtime: ${info.runtime} ${info.runtimeVersion}`);
        console.log(`  description: ${info.description}`);
        console.log("");
      }
    });
}