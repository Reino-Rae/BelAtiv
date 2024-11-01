#### 1. Tempo utilizado para calcular a matriz resultante
- **Tempo para multiplicação da matriz**: 12 ms
- A multiplicação da matriz de adjacência por ela mesma para obter \( A^2 \) levou **12 ms**. Essa etapa envolve operações aritméticas (somas e multiplicações) realizadas em paralelo para cada linha da matriz, distribuídas entre as threads. O tempo é relativamente baixo devido ao pequeno tamanho da matriz (5x5) e ao uso do paralelismo, que acelera o processamento.

#### 2. Tempo para calcular as pessoas com mais amigos em comum
- **Tempo para encontrar amigos em comum**: 63 ms
- Identificar os usuários com mais amigos em comum (Alice e Bob) levou **63 ms**, o que é surpreendentemente maior do que o tempo para a multiplicação da matriz. Esse tempo mais elevado pode ser influenciado por fatores como o overhead de acesso sequencial à matriz resultante e a falta de paralelização nessa etapa.

#### 3. Tempo para calcular a pessoa mais influente
- **Tempo para encontrar o usuário mais influente**: 6 ms
- A identificação do usuário mais influente (Alice) levou **6 ms**. Como essa etapa envolve apenas a soma dos elementos de cada coluna da matriz \( A^2 \), é uma operação direta e rápida, justificando o tempo menor em comparação com o cálculo de amigos em comum.

---

### Discussão das Diferenças de Tempo

- **Multiplicação da Matriz (12 ms)**: Este é o tempo necessário para a operação mais complexa, mas o paralelismo usado reduz significativamente o tempo de execução.
- **Encontrar Amigos em Comum (63 ms)**: Este tempo, maior do que o esperado, pode ocorrer devido à falta de paralelização nesta etapa ou à sobrecarga associada ao acesso sequencial à matriz. Em geral, operações de soma de linhas são rápidas, o que sugere que o ambiente de execução pode ter afetado o desempenho.
- **Encontrar o Mais Influente (6 ms)**: Esta operação é a mais rápida devido à simplicidade da tarefa de soma de colunas.

Essas diferenças mostram como o paralelismo melhora o desempenho na multiplicação da matriz, enquanto a busca sequencial para identificar amigos em comum pode levar mais tempo, especialmente em matrizes maiores ou ambientes de execução com maior carga.

---

### Resumo dos Tempos de Execução
- **Tempo total de execução**: 108 ms
- Abaixo está um resumo dos tempos individuais:
  - **Multiplicação da matriz**: 12 ms
  - **Cálculo de amigos em comum**: 63 ms
  - **Identificação do usuário mais influente**: 6 ms
