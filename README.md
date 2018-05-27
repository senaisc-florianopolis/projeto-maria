# Projeto Maria [![License: CC0-1.0](https://img.shields.io/badge/License-CC0%201.0-lightgrey.svg)](http://creativecommons.org/publicdomain/zero/1.0/) [![Build Status](https://travis-ci.org/senaisc-florianopolis/projeto-maria.svg?branch=master)](https://travis-ci.org/senaisc-florianopolis/projeto-maria)

O Projeto Maria é uma ferramenta de automação de estimativa de vendas com base em métodos estatísticos.

Ele está sendo desenvolvido como projeto integrador das disciplinas e fases do [Curso Superior de Tecnologia em Análise e Desenvolvimento de Sistemas](https://www.sc.senai.br/siteinstitucional/curso/superior-de-tecnologia-em-analise-e-desenvolvimento-de-sistemas/3404).

## Instalação

### Requisitos

* Java SE 8
* MySQL 8

## Uso

```
Usage: <main class> [options] [command] [command options]
  Options:
    -h, -?, --help
      Help
    -v, --verbose
      Verbose mode
      Default: false
  Commands:
    carga      Executa a inserção, remoção e atualização de registros
      Usage: carga [options] <caminho para o arquivo de entrada>
        Options:
          -d, --delete
            Remoção de registros
            Default: false
          -i, --insert
            Inserção de registros
            Default: false
        * -t, --tipo
            Tipo da carga
            Possible Values: [produto, familia, canal, historico]
          -u, --update
            Atualização de registros
            Default: false

    relatorio      Geração de relatórios
      Usage: relatorio [options] <caminho para o arquivo de saída>
        Options:
        * -t, --tipo
            Tipo do relatório
            Possible Values: [produto, familia, canal, historico, estimativa]
```

## Desenvolvimento

...

## Contribuições

*Pull requests* são sempre bem-vindos, porém destacamos que neste momento o projeto tem fins educacionais. 

## Licença

[![CC0](https://licensebuttons.net/p/zero/1.0/88x31.png)](https://creativecommons.org/publicdomain/zero/1.0/)
