# SRE-Planetarium-Resources
Base Spring-Boot Planetarium app, locust app to simulate users, and SQL script to set up the Planetarium database

# Docker Images
- Planetarium
    - esuminski/p3planetarium
- Locust App
    - esuminski/p3planetarium-locust

# Locust App Notes
- The application only uses the Locust package, no extra installs are necessary at this time
- Host information needs to be provided in the web ui, there is currently no default
- Locust runs on port 8089

# Planetarium App Notes
- Planetarium is not currently set to run tests on a separate database
- Tests require delete records to be reset (found in setup-reset.sql)
- Planetarium runs on port 8080
- Requires environment variables as is to provide database url, username, and password
