# Running with Docker

Set environment variables (Linux/Mac):

1. Set environment variables as you prefer in [set_env_var.sh]
2. From the main directory run `source ./set_env_var.sh`

Set environment variables (Windows):

1. Set environment variables as you prefer in [set_env_var.bat]
2. From the main directory run `.\set_env_var.bat`

Set Environment Variables (IntelliJ):
1. See README file -> Running build -> Environment variables
2. Copy paste it into IntelliJ terminal

Run Docker & Gradle Build:

3. From the same shell run `docker-compose up -d`
4. From the same shell now you can run gradle scripts (e.g. `./gradlew test`)