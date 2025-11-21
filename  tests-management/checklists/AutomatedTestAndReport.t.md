# AutomatedTestAndReport

Objetivo:
Este Test Suite descreve os tipos de operações e validações necessárias
para garantir o correto funcionamento dos elementos principais do jogo Battleship,
incluindo navios, sistemas de navegação e posicionamento.

Operações a executar:

Validação de Navios (Ships):
- Verificar criação e propriedades dos diferentes tipos de navios:
    * ShipTest
    * BargeTest
    * CaravelTest
    * FrigateTest
    * GalleonTest
- Confirmar integridade das dimensões e orientações dos navios
- Validar comportamento dos navios em situações de dano ou afundamento

Validação de Sistemas:
- Validar funcionamento do sistema de bússola:
    * CompassTest
- Avaliar correto agrupamento e gestão de frotas:
    * FleetTest
- Verificar sistema de posicionamento do navio Caravel:
    * ShipPositionCaravelTest