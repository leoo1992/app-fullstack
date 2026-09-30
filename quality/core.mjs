/** @param {number} passed @param {number} total */
export function qualityPercent(passed, total) {
  if (!Number.isFinite(passed) || !Number.isFinite(total) || total <= 0) return 0;
  return Math.round((passed / total) * 100);
}

/** @param {string} path */
export function isSensitivePath(path) {
  const normalized = path.toLowerCase();
  if (/(^|\/)\.env(?:\.|$)/.test(normalized) && !/\.(example|sample|template)$/.test(normalized)) return true;
  return /\.(pem|key|p12|pfx|jks|keystore)$/.test(normalized);
}
