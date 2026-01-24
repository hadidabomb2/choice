const LABEL_SEPARATOR = ": ";

function formatLabelMessage(label, message) {
  return `${label}${LABEL_SEPARATOR}${message}`;
}

export const textFormatter = {
  format(label, message) {
    return formatLabelMessage(label, message);
  }
};

export const jsonFormatter = {
  format(label, message) {
    return JSON.stringify({ label, message });
  }
};
