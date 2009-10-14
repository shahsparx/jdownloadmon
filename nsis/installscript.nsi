; The name of the installer
Name "jDownloadMon Installer"

; The file to write
OutFile "jDownloadMon.0.1.installer.exe"

; The default installation directory
InstallDir $PROGRAMFILES\jDownloadMon

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\jDownloadMon" "Install_Dir"

; Request application privileges for Windows Vista
RequestExecutionLevel admin

;--------------------------------

; Pages

Page components
Page directory
Page instfiles

UninstPage uninstConfirm
UninstPage instfiles

;--------------------------------

; The stuff to install
Section "jDownloadMon (required)"

  SectionIn RO
  
  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File "jDownloadMon.jar"
  File "jDownloadMon.ico"
  SetOutPath $INSTDIR\lib
  File "lib\commons-codec-1.4.jar"
  File "lib\commons-httpclient-3.1.jar"
  File "lib\commons-logging-1.1.1.jar"
  
  ;Set working directory back
  SetOutPath $INSTDIR
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\jDownloadMon "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\jDownloadMon" "DisplayName" "jDownloadMon"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\jDownloadMon" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\jDownloadMon" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\jDownloadMon" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"

  CreateDirectory "$SMPROGRAMS\jDownloadMon"
  CreateShortCut "$SMPROGRAMS\jDownloadMon\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\jDownloadMon\jDownloadMon.lnk" "$INSTDIR\jDownloadMon.jar" "" "$INSTDIR\jDownloadMon.ico" 0
  
SectionEnd

Section "Desktop Shortcut"

CreateShortCut "$DESKTOP\jDownloadMon.lnk" "$INSTDIR\jDownloadMon.jar" "" "$INSTDIR\jDownloadMon.ico" 0

SectionEnd

;--------------------------------

; Uninstaller

Section "Uninstall"
  
  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\jDownloadMon"
  DeleteRegKey HKLM SOFTWARE\jDownloadMon

  ; Remove files and uninstaller
  Delete $INSTDIR\*.*
  Delete $INSTDIR\lib\*.*

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\jDownloadMon\*.*"
  Delete "$DESKTOP\jDownloadMon.lnk"

  ; Remove directories used
  RMDir "$SMPROGRAMS\jDownloadMon"
  RMDir "$INSTDIR\lib"
  RMDir "$INSTDIR"

SectionEnd
