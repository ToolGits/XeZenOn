# XeZenOn 🤖

[![Status](https://img.shields.io/badge/status-Stable-brightgreen)](https://github.com/ToolGits/XeZenOn)
[![Version](https://img.shields.io/badge/version-1.1.1-blue)](https://github.com/ToolGits/XeZenOn)
[![Platform](https://img.shields.io/badge/platform-Android-green)](https://www.android.com/)
[![Language](https://img.shields.io/badge/language-Kotlin-purple)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/license-MPL--2.0-orange)](LICENSE)

> A lightweight semi-AI focused on simple, modular, and gradually evolving intelligent systems.

> [!IMPORTANT]
> **XeZenOn 1.1.1 is Stable!** 🎉

## 🚀 About

**XeZenOn** is a **ToolGits** project exploring the development of a lightweight semi-AI for Android.

Its intelligence is built around rules, intents, language detection, responses, and a local knowledge system.

## 🧠 BoxHead

**BoxHead** is the core of XeZenOn.

- `braindroid.kt` — Main brain
- `rules.kt` — Behavioral rules
- `language.kt` — Language detection
- `intents.kt` — Intent detection
- `responses.kt` — Response generation
- `KnowledgeEngine.kt` — Knowledge loading and search
- `KnowledgeBase.kt` — Knowledge storage

## 📚 Learned

XeZenOn uses a local knowledge system stored in the Android application assets.

The official knowledge directory is:

`app/src/main/assets/Learned/`

The `Learned/` directory contains the knowledge files used by the **KnowledgeEngine**.

The previous knowledge directory:

`BoxHead/Learned/`

is now considered **Legacy** and is no longer used as the active knowledge source.

## 🌍 Languages

XeZenOn currently supports:

- 🇧🇷 Portuguese
- 🇺🇸 English
- 🇩🇪 German
- 🇧🇬 Bulgarian
- 🇪🇸 Spanish

> [!TIP]
> XeZenOn can use the Android device language as a fallback.

## ✨ Features

- 🧠 Lightweight semi-AI
- 🧩 Modular architecture
- 🌍 Multilingual interaction
- 🎯 Intent detection
- 📚 Local knowledge system
- 📜 Rule-based behavior
- 💾 Learned responses
- 🧠 Knowledge loading through Android Assets
- 📱 Android native application

## 🔧 Development

| Component | Technology |
|---|---|
| Platform | Android |
| Language | Kotlin |
| Compiler | Kotlin K2 |
| Build | Gradle |
| AGP | Android Gradle Plugin |
| Compile SDK | API 36 |
| Minimum SDK | API 23 |

### Build

Clone the repository and run:

`git clone https://github.com/ToolGits/XeZenOn.git`

`cd XeZenOn`

`./gradlew assembleDebug`

APK output:

`app/build/outputs/apk/debug/app-debug.apk`

## 🧪 Stable

**XeZenOn 1.1.1** is the current stable release.

The core system has been tested on real Android hardware, including:

- ✅ Application startup
- ✅ Brain processing
- ✅ Language detection
- ✅ Intent detection
- ✅ Knowledge loading
- ✅ Knowledge lookup
- ✅ Response generation
- ✅ Multilingual responses

> [!NOTE]
> Stable does not mean development has stopped. Future versions can continue improving the brain, knowledge system, reasoning, and user interface.

## 🛣️ Future

- 🎨 Improved UI
- 🧠 More advanced reasoning
- 📚 Expanded knowledge
- 🌍 More languages
- 🎯 More intents
- ⚙️ Improved Android integration
- 🧠 Better conversational context

## 🏢 ToolGits

XeZenOn is maintained by **ToolGits**.

- Organization: https://github.com/ToolGits
- Creator: https://github.com/enzobobdevvideos04-ctrl

## 📜 License

XeZenOn is licensed under the **Mozilla Public License 2.0 (MPL-2.0)**.

See [`LICENSE`](LICENSE) for the full license.

---

<p align="center">
  <strong>XeZenOn — Small brain. Big evolution. 🤖</strong>
</p>