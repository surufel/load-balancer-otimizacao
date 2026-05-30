# Load Balancer Otimização: AVL vs Red-Black Tree

Projeto acadêmico desenvolvido para a disciplina de Estruturas de Dados do **iCEV (Instituto de Ensino Superior)**. O objetivo deste laboratório é implementar, testar e comparar o desempenho de duas das principais árvores binárias de busca balanceadas (AVL e Red-Black Tree) aplicadas ao gerenciamento de regras de um balanceador de carga de redes.

---

## Objetivo do Projeto

Em redes de alta demanda, roteadores e balanceadores de carga precisam decidir rapidamente o destino de milhões de pacotes. Este projeto simula esse ambiente armazenando "Regras de Roteamento" (Packet Rules) em estruturas de dados complexas, avaliando o custo computacional (*trade-off*) entre a rigidez estrutural da **Árvore AVL** e a flexibilidade da **Árvore Rubro-Negra (RBT)**.

## Funcionalidades e Arquitetura

O projeto foi dividido em três pilares principais:

1. **Core Estrutural:**
   - Implementação completa da Árvore AVL (com controle rígido de Fator de Balanceamento e Rotações).
   - Implementação completa da Árvore Red-Black (com balanceamento focado em regras de coloração e altura-negra).
   - Operações CRUD para regras de roteamento (Inserção, Busca e Deleção).

2. **Quality Assurance (QA) e Validação:**
   - Auditoria automatizada pós-estresse para garantir a integridade das invariantes matemáticas de ambas as árvores.
   - Contadores internos acoplados aos métodos de rotação (Simples e Duplas) para medir o esforço computacional real.

3. **Motor de Stress Test:**
   - Laboratório de benchmarking capaz de injetar cargas incrementais de dados (1.000, 10.000, 100.000 e 1.000.000 de pacotes).
   - Geração pseudoaleatória de *IDs* com semente fixa para garantir testes determinísticos e justos entre as duas estruturas.

---

## Resultados do Benchmark (1 Milhão de Pacotes)

Durante a submissão de ambas as árvores à carga máxima de estresse, obtivemos os seguintes resultados práticos que comprovam a complexidade $O(\log n)$:

| Estrutura | Operação | Tempo de Execução | Esforço (Rotações) | Status QA |
| :---: | :--- | :--- | :--- | :---: |
| **AVL** | Inserção | $\approx$ 1,11s *(1.108.644.137 ns)* | 442.554 |  OK |
| **RBT** | Inserção | $\approx$ 1,05s *(1.055.904.117 ns)* | **368.510** |  OK |
| **AVL** | Busca | **$\approx$ 0,72s** *(721.223.145 ns)* | N/A |  OK |
| **RBT** | Busca | $\approx$ 0,76s *(766.056.687 ns)* | N/A |  OK |

> **Conclusão Prática:** A Red-Black Tree obteve uma redução de cerca de 16,7% no número de rotações durante a inserção, sendo mais rápida para cenários de alta escrita. Em contrapartida, o balanceamento perfeito da AVL garantiu caminhos mais curtos, tornando-a vencedora no cenário de buscas intensivas.

---

## Tecnologias Utilizadas

* **Linguagem:** Java
* **Controle de Versão:** Git & GitHub
* **Ambiente de Desenvolvimento:** IntelliJ IDEA

---
