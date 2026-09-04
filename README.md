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

Its intelligence is built around rules, intents, language detection, responses, learned responses, conversation history, and a local knowledge system.

XeZenOn is designed to evolve gradually through modular systems without relying on a large external AI model.

## 🧠 BoxHead

**BoxHead** is the core intelligence system of XeZenOn.

It coordinates the main processing stages used to understand an input and generate a response.

| Component | Description |
|---|---|
| `braindroid.kt` | Main brain and processing pipeline |
| `rules.kt` | Behavioral rules and restrictions |
| `language.kt` | Language detection |
| `intents.kt` | Intent detection |
| `responses.kt` | Response generation |
| `KnowledgeEngine.kt` | Knowledge loading, searching, and management |
| `KnowledgeBase.kt` | Knowledge storage and representation |

The BoxHead system is designed to keep the intelligence modular, allowing individual systems to evolve without requiring the entire application to be redesigned.

## 📚 Learned

XeZenOn includes a local knowledge system for providing information without depending on an external AI model.

The active knowledge directory is:

`app/src/main/assets/Learned/`

The `Learned/` directory contains the knowledge files loaded by the **KnowledgeEngine**.

The previous knowledge directory:

`BoxHead/Learned/`

is now considered **Legacy** and is no longer used as the active knowledge source.

The KnowledgeEngine can load the available knowledge, search through it, and provide relevant information to the BoxHead.

## 🌍 Languages

XeZenOn currently supports:

- 🇧🇷 Portuguese
- 🇺🇸 English
- 🇩🇪 German
- 🇧🇬 Bulgarian
- 🇪🇸 Spanish

> [!TIP]
> XeZenOn can use the Android device language as a fallback when the input language cannot be confidently detected.

## ✨ Features

- 🧠 Lightweight semi-AI
- 🧩 Modular intelligence architecture
- 🌍 Multilingual interaction
- 🎯 Intent detection
- 📜 Rule-based behavior
- 💾 Learned responses
- 📚 Local knowledge system
- 🔎 Local knowledge lookup
- 🧠 Knowledge loading through Android Assets
- 💬 Conversational message history
- 📜 Scrollable conversation view
- 🗨️ Separate user and XeZenOn message bubbles
- ⌨️ Native text input
- 📤 Message sending through the Android UI
- 📱 Native Android application

## 🎨 Interface

XeZenOn 1.1.1 includes an improved native Android chat interface designed to keep the application simple and lightweight.

The interface provides:

- 🤖 XeZenOn title and subtitle
- 💬 Accumulated conversation history
- 👤 User messages displayed separately
- 🤖 XeZenOn responses displayed separately
- 📜 Automatic scrolling to the latest message
- ⌨️ Native text input field
- 📤 Send button
- 📱 Native Android components

The interface is intentionally lightweight and built directly with Android native components.

## 🔧 Development

| Component | Technology |
|---|---|
| Platform | Android |
| Language | Kotlin |
| Compiler | Kotlin K2 |
| Build System | Gradle |
| Build Plugin | Android Gradle Plugin |
| Compile SDK | API 36 |
| Target SDK | API 36 |
| Minimum SDK | API 23 |

### Build

Clone the repository and run:

```bash
git clone git@github.com:ToolGits/XeZenOn.git
cd XeZenOn
./gradlew assembleDebug
```

The generated debug APK can be found at:

`app/build/outputs/apk/debug/app-debug.apk`

## 🧪 Stable

**XeZenOn 1.1.1** is the current stable release.

The core system and Android application have been tested on real Android hardware, including:

- ✅ Application startup
- ✅ Brain processing
- ✅ Language detection
- ✅ Intent detection
- ✅ Rule processing
- ✅ Knowledge loading
- ✅ Knowledge lookup
- ✅ Learned responses
- ✅ Response generation
- ✅ Multilingual responses
- ✅ Conversational message history
- ✅ Scrollable chat interface
- ✅ Native Android UI

> [!NOTE]
> Stable does not mean development has stopped. Future versions can continue improving the brain, knowledge system, reasoning, conversation context, and user interface.

## 🛣️ Future

| Area | Planned Improvements |
|---|---|
| 🎨 Interface | Further UI improvements |
| 🧠 Intelligence | More advanced reasoning |
| 📚 Knowledge | Expanded knowledge and retrieval |
| 🌍 Languages | Additional language support |
| 🎯 Intents | More intent types |
| ⚙️ Android | Improved Android integration |
| 💬 Conversation | Better conversational context |
| 💾 Memory | More persistent memory |
| 🔎 Knowledge | Improved knowledge retrieval |

## 🏢 ToolGits

XeZenOn is maintained by **ToolGits**.

- Organization: https://github.com/ToolGits
- Creator: https://github.com/enzobobdevvideos04-ctrl

XeZenOn is part of the ToolGits ecosystem alongside other projects developed by the organization.

## 📜 License

XeZenOn is licensed under the **Mozilla Public License 2.0 (MPL-2.0)**.

See [`LICENSE`](LICENSE) for the full license.

---

<p align="center">
  <strong>XeZenOn — Small brain. Big evolution. 🤖</strong>
</p>