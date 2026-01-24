#!/usr/bin/env node
import { Command } from "commander";
import { registerInitCommand } from "./commands/init.js";
import { registerListCommand } from "./commands/list.js";
import { registerPluginsCommand } from "./commands/plugins.js";

const program = new Command();

program
  .name("choice")
  .description("Install language templates and frameworks")
  .version("0.1.0");

registerInitCommand(program);
registerListCommand(program);
registerPluginsCommand(program);

program.parse(process.argv);