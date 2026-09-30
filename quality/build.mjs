import { mkdir, readdir, stat, writeFile } from 'node:fs/promises';
import path from 'node:path';

const ignored = new Set(['.git', 'node_modules', 'quality-dist']);
const files = [];

async function walk(dir) {
  for (const name of await readdir(dir)) {
    if (ignored.has(name)) continue;
    const full = path.join(dir, name);
    const info = await stat(full);
    if (info.isDirectory()) await walk(full);
    else files.push(full.replaceAll('\\', '/'));
  }
}

await walk('.');
await mkdir('quality-dist', { recursive: true });
await writeFile(
  'quality-dist/manifest.json',
  JSON.stringify({ generatedAt: new Date().toISOString(), files: files.sort() }, null, 2),
);
console.log(`Quality manifest: ${files.length} files`);
