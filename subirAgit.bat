@echo off
echo Iniciando automatización de Git...

:: Cambia a la rama principal (main o master)
git checkout main

:: Añadir todos los archivos al área de preparación (staging)
git add .

:: Realizar un commit con un mensaje predeterminado
git commit -m "Actualización automática"

:: Realizar un push hacia la rama principal (cambia 'origin' y 'main' según tu configuración)
git push origin main

:: Pausa para ver los resultados
pause
