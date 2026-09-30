# Arquitetura

Este repositório preserva sua implementação original e adiciona uma camada isolada de engenharia em `quality/`.

## Quality layer

- verificação estática tipada do tooling;
- lint automatizado;
- testes com cobertura mínima de 80%;
- geração de manifesto de build;
- container não-root para validação;
- GitHub Actions como quality gate.
