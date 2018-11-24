# Projeto Maria 

[![License: CC0-1.0](https://img.shields.io/badge/License-CC0%201.0-lightgrey.svg)](http://creativecommons.org/publicdomain/zero/1.0/) 
[![Build Status](https://travis-ci.org/senaisc-florianopolis/projeto-maria.svg?branch=master)](https://travis-ci.org/senaisc-florianopolis/projeto-maria)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=br.senai.sc.edu:projeto-maria&metric=coverage)](https://sonarcloud.io/component_measures?id=br.senai.sc.edu%3Aprojeto-maria&metric=coverage)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=br.senai.sc.edu:projeto-maria&metric=bugs)](https://sonarcloud.io/component_measures?id=br.senai.sc.edu%3Aprojeto-maria&metric=bugs)
[![SonarCloud Code Smells](https://sonarcloud.io/api/project_badges/measure?project=br.senai.sc.edu:projeto-maria&metric=code_smells)](https://sonarcloud.io/component_measures?id=br.senai.sc.edu%3Aprojeto-maria&metric=code_smells)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=br.senai.sc.edu:projeto-maria&metric=vulnerabilities)](https://sonarcloud.io/component_measures?id=br.senai.sc.edu%3Aprojeto-maria&metric=vulnerabilities)
[![SonarCloud Duplicated Lines](https://sonarcloud.io/api/project_badges/measure?project=br.senai.sc.edu:projeto-maria&metric=duplicated_lines_density)](https://sonarcloud.io/component_measures?id=br.senai.sc.edu%3Aprojeto-maria&metric=duplicated_lines_density)

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
  Commands:
    carga      Executa a inserção, remoção e atualização de registros
      Usage: carga [options] <caminho para o arquivo de entrada>
        Options:
          -j, --json
            Retorno em formato JSON
            Default: false
        * -t, --tipo
            Tipo da carga
            Possible Values: [PRODUTO, FAMILIA, CANAL, HISTORICO, PHASE]

    relatorio      Geração de relatórios
      Usage: relatorio [options] <caminho para o arquivo de saída>
        Options:
          -j, --json
            Retorno em formato JSON
            Default: false
          -pa, --periodoanterior
            Periodo anterior (obrigatório para o tipo ESTIMATIVA)
            Default: 0
        * -t, --tipo
            Tipo do relatório
            Possible Values: [PRODUTO, FAMILIA, CANAL, HISTORICO, ESTIMATIVA, PHASE]



```

## Desenvolvimento

...

## Contribuições

*Pull requests* são sempre bem-vindos, porém destacamos que neste momento o projeto tem fins educacionais. 

## Licença

[![CC0](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/CC-Zero-badge.svg/88px-CC-Zero-badge.svg.png)](https://creativecommons.org/publicdomain/zero/1.0/)
