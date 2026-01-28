export type TemplateInfo = {
  latest: string;
  versions: string[];
  runtime: string;
  runtimeVersion: string;
  description: string;
};

export type TemplateIndex = Record<string, TemplateInfo>;

export interface Plugin {
  name: string;
  version: string;
  description: string;
  url: string;
}

export interface PluginRegistry {
  plugins: Plugin[];
}