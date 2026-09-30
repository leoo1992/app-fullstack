import test from 'node:test';
import assert from 'node:assert/strict';
import { isSensitivePath, qualityPercent } from '../quality/core.mjs';

test('qualityPercent calcula a pontuação', () => {
  assert.equal(qualityPercent(20, 20), 100);
  assert.equal(qualityPercent(16, 20), 80);
  assert.equal(qualityPercent(0, 0), 0);
});

test('isSensitivePath bloqueia segredos versionados', () => {
  assert.equal(isSensitivePath('.env'), true);
  assert.equal(isSensitivePath('config/private.key'), true);
  assert.equal(isSensitivePath('.env.example'), false);
  assert.equal(isSensitivePath('src/index.js'), false);
});
