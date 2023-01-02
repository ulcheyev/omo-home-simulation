#  OMO Smart Home :house:
V našem projektu byl cíl vytvořit simulaci života v domě. Simulace životů lidí a jejich mazlíčků, jejich interakce s předměty v domě, reakce na některé události, které se odehrávají ve stěnách domu a venku. Také v domě jsou bezpečnostní senzory se systémem oznámení. Potřebné informace o domu jsou zaznamenány ve reportech: spotřeba zdrojů, události, které se vyskytly v domě, konfigurace domu atd. Pro vygenerování reportu stačí spustit naši aplikaci.

## Základní instrukce :page_facing_up:

Při spuštění simulace si budete muset nejprve vybrat, zda chcete dostávat e-mailová upozornění v případě poplachu, poté zadejte svůj e-mail a vyberte počet dní simulace.

## Funkční požadavky :hammer_and_wrench:

<details> <summary>Koukni se</summary> 

- **F1.** [+] Entity se kterými pracujeme je dům, okno (+ venkovní žaluzie), patro v domu, senzor, zařízení (=spotřebič), osoba, auto, kolo, domácí zvíře jiného než hospodářského typu, plus libovolné další entity

[-] Okna nemají žaluzie

- **F2.** [+] Jednotlivá zařízení v domu mají API na ovládání. Zařízení mají stav, který lze měnit pomocí API na jeho ovládání. Akce z API jsou použitelné podle stavu zařízení. Vybraná zařízení mohou mít i obsah - lednice má jídlo, CD přehrávač má CD.

- **F3.** [+] Spotřebiče mají svojí spotřebu v aktivním stavu, idle stavu, vypnutém stavu.

- **F4.** [+] Jednotlivá zařízení mají API na sběr dat o tomto zařízení. O zařízeních sbíráme data jako spotřeba elektřiny, plynu, vody a funkčnost (klesá lineárně s časem).

- **F5.** [+] Jednotlivé osoby a zvířata mohou provádět aktivity(akce), které mají nějaký efekt na zařízení nebo jinou osobu.

- **F6.** [+] Jednotlivá zařízení a osoby se v každém okamžiku vyskytují v jedné místnosti (pokud nesportují) a náhodně generují eventy (eventem může být důležitá informace a nebo alert).

- **F7.** [+] Eventy jsou přebírány a odbavovány vhodnou osobou (osobami) nebo zařízením (zařízeními).

- **F8.** [+] Vygenerování reportů:

  - [+] HouseConfigurationReport: veškerá konfigurační data domu.

  - [+] EventReport: report eventů.
  -	[+] ActivityAndUsageReport: Report akcí (aktivit) jednotlivých osob a zvířat.
  -	[+] ConsumptionReport: Kolik jednotlivé spotřebiče spotřebovaly elektřiny, plynu, vody. Včetně finančního vyčíslení.

- **F9.** [+] Při rozbití zařízení musí obyvatel domu prozkoumat dokumentaci k zařízení - najít záruční list, projít manuál na opravu a provést nápravnou akcí.

- **F10.** [+] Rodina je aktivní a volný čas tráví zhruba v poměru (50% používání spotřebičů v domě a 50% sport kdy používá sportovní náčiní kolo nebo lyže). Když není volné zařízení nebo sportovní náčiní, tak osoba čeká.

</details>

## Nefunkční požadavky :white_check_mark:

<details> <summary>Koukni se</summary> 

- **F1.** [+] Není požadována autentizace ani autorizace.

- **F2.** [+] Aplikace může běžet pouze v jedné JVM.

- **F3.** [+] Aplikaci pište tak, aby byly dobře schované metody a proměnné, které nemají být dostupné ostatním třídám.

- **F4.** [+] Reporty jsou generovány do textového souboru.

- **F5.** [+] Konfigurace domu, zařízení a obyvatel domu může být nahrávána přímo z třídy nebo externího souboru (preferován je json).

 </details>

## Design Patterny :art:

<details> <summary>Koukni se</summary> 

- **DP1.**  State machine
  - Mění spotřebu určitých zdrojů zařízeními v závislosti na jejich stavu.
- **DP2.**  Iterator
  - Používá se při vytváření EventReport, ConsummentReport. Umožňuje iterativně obejít všechny objekty a shromáždit všechny data v kolekci s následným generováním reportu.
- **DP3.**  Factory/Factory method
- **DP4.**  Singleton
  - Používá se ve třídě vytváření náhodných celočíselných hodnot pro účely následného použití při generování eventu. To zaručuje jedinečnou hodnotu.
- **DP5.**  Null Object Pattern
- **DP6.**  Observer
  - Pokud jsou aktivovány senzory bezpečnostní , bude to oznámeno všemi nezbytnými prostředky.
- **DP7.**  Lazy Initialization
  - Používá se ke generování dokumentace pouze v případě, že je zařízení rozbité.
- **DP8.**  Visitor
  - Používá se při vytváření HouseConfigurationReport. Umožňuje přidat operaci pro získání všech dat o objektů bez provádění změn ve stávající struktuře objektů.
- **DP9.**  Builder
  - Používá se k postupnému vytváření objektů, jako je zařízení, osoba, zvíře, vozidlo.
- **DP10.**  Composite
- **DP11.**  Strategy

 </details>

## Class Diagram :bar_chart:
[Class diagram v.01](https://gitlab.fel.cvut.cz/ulcheyev/lupenmar_ulcheyev_omo/-/blob/8ca68f36d484200e58f284b05b50ff08e2ec2a9c/diagrams/class_diagram_omo.drawio.pdf)

[Class diagram v.02](https://gitlab.fel.cvut.cz/ulcheyev/lupenmar_ulcheyev_omo/-/blob/main/diagrams/class_diagram_v02.png)
## Use-case diagram :construction_worker:
[Use-case diagram](https://gitlab.fel.cvut.cz/ulcheyev/lupenmar_ulcheyev_omo/-/blob/main/diagrams/use_case_omo.drawio.pdf)

