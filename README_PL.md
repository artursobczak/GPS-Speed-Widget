# GPS Speed Widget

Minimalny widget dla Nova Launcher:
- duża liczba prędkości,
- km/h,
- GPS urządzenia,
- przezroczyste tło,
- aktualizacja na żywo przez usługę pierwszoplanową.

## Ważne

Ten projekt jest przygotowany pod Lenovo TB300FU / Android 13. Nie wymaga KWGT, Torque, MacroDroid ani OBD.

Aplikacja musi być uruchomiona przynajmniej raz i mieć pozwolenie na dokładną lokalizację. Następnie widget można dodać w Nova Launcher → Widgety → GPS Speed Widget.

## Budowanie APK

Repozytorium zawiera GitHub Actions. Po wrzuceniu projektu do GitHuba workflow `Build APK` zbuduje `app-debug.apk` automatycznie.

## Uwaga

Android może ograniczać pracę GPS w tle zależnie od ustawień oszczędzania baterii. Jeśli widget przestanie aktualizować się podczas jazdy, dla tej aplikacji należy wyłączyć optymalizację baterii.
