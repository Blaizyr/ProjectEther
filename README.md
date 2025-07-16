# Project Ether

⚔️ Compose Multiplatform | 🧠 Decompose Navigation | 🎮 Godot Embedded Engine | 🌐 Ktor WebSocket Client | 🛡️ Login Form with Validation | 🔌 Koin DI 

---

## ✨  Description | Opis | 

> An experimental 3D environment/game built using Compose Multiplatform and an embedded Godot engine.  
> Merges UI, backend, and game logic into a unified Kotlin-based stack.

> Eksperymentalna gra/środowisko 3D zbudowane z Compose Multiplatform i wbudowanym silnikiem Godot.  
> Połączenie świata UI, backendu i silnika gier – wszystko w jednym ekosystemie Kotlinowym.

🇫🇷 _Un environnement expérimental en 3D construit avec Compose Multiplatform et le moteur Godot embarqué._  
🇸🇪 _Ett experimentellt 3D-miljö byggd med Compose Multiplatform och inbäddad Godotmotor._
🇳🇴 _Et eksperimentelt 3D-miljø bygget med Compose Multiplatform og innebygd Godot-motor._


---

## 🔧 Technologies

- Kotlin Multiplatform (Android / Desktop / JS-WASM / iOS - in future)
- Compose Multiplatform
- Decompose Navigation
- Embedded Godot Engine (native bridge)
- Ktor WebSockets
- Coroutine-based validation
- Result-based use case pattern

---

## 🧠 Architecture

- `LoginComponent`  
  Handles form state, debounce, validation, and login trigger via `onLoginSuccess`.

- `GameComponent`  
  Starts embedded Godot client.

- `App`  
  Entry point and navigation host (`Decompose` child stack).

- `gameClient`  
  Manages WebSocket communication with `/ws` backend.

- `PlayerRepository`  
  In-memory session and player store (to be persisted).

- `loginUseCase`  
  Triggers server handshake, returns `Result<Session>`.

---

## 📝 TODO

### Backend
- [ ] Password hashing & backend validation
- [ ] Session persistence on client and server
- [ ] MFA / token / captcha
- [ ] Async backend validation (username availability, password strength)

### Frontend
- [ ] WASM, iOS `launchGodotClient()` implementation
- [ ] GameClient ↔ GodotClient integration (engine control)
- [ ] Network state & input synchronization
- [ ] UI/UX transitions on login/logout/game screens

### Quality
- [ ] Test coverage: unit, integration, UI (Compose)

<!--
---

## 🚀 Launch / Uruchamianie

🖥️ Desktop (Compose Multiplatform)
./gradlew :composeApp:run

🌐 WebAssembly (WASM via JS)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

📱 Android App
./gradlew :composeApp:installDebug or use Android Studio manually

☁️ Backend Server (Ktor Application)
./gradlew :server:run

🔎 Optional – list all runnable tasks
./gradlew tasks --all
-->
---

## 📜 License

MIT License. See [`LICENSE`](./LICENSE) for details.

---

## 🧘‍♂️ Reflections

> “This project has ADHD and caffeine in its DNA.  
> What doesn't work — will work.  
> What works — will eventually evolve.”

> _Code is clay.  
> Reality is a dream we compile into existence._

---
