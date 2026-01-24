export type TemplateInfo = {
  latest: string;
  versions: string[];
  runtime: string;
  runtimeVersion: string;
  description: string;
};

export type TemplateIndex = Record<string, TemplateInfo>;