export const textFormatter = {
  format(label, message) {
    return `${label}: ${message}`;
  }
};

export const jsonFormatter = {
  format(label, message) {
    return JSON.stringify({ label, message });
  }
};
