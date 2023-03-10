from dataclasses import asdict
from locust import HttpUser, between, task
from entities.moon import Moon
from state.moon_state import TestMoons
from state.planet_state import TestPlanets
from state.user_state import TestUsers


class Swarm(HttpUser):
    wait_time = between(1, 5)

    def on_start(self):
        """logs in User generated by Locust"""
        self.client.post("/login", json=asdict(TestUsers.get_random_user()))

    def on_stop(self):
        """logs out User generated by Locust"""
        self.client.post("/logout")

    @task
    def register(self):
        """creates a new User"""
        with self.client.post("/register", json=asdict(new_user := TestUsers.generate_user()),
                              catch_response=True) as response:
            if response.status_code == 201:
                response_dict: dict = eval(response.text)
                new_user.userId = response_dict.get("userId")
                TestUsers.users.append(new_user)
            else:
                response.failure(response.text)

    @task
    def create_planet(self):
        """creates a new Planet and stores it in the TestPlanets' planets list for future use"""
        with self.client.post("/api/planet", json=asdict(new_planet := TestPlanets.generate_planet()),
                              catch_response=True) as response:
            if response.status_code == 201:
                response_dict: dict = eval(response.text)
                new_planet.planetId = response_dict.get("planetId")
                TestPlanets.planets.append(new_planet)
                response.success()
            else:
                print(f"CREATE PLANET: {response.text}")
                response.failure(response.text)

    @task
    def delete_planet(self):
        """deletes a Planet and removes it from the TestPlanets' planets list"""
        planet = TestPlanets.get_random_planet()
        with self.client.delete(f"/api/planet/{planet.planetName}", name="/api/planet/{name}",
                                catch_response=True) as response:
            if response.status_code == 200:
                try:
                    TestPlanets.planets.remove(planet)
                except ValueError:
                    # this prevents an uncaught exception from causing issues with the locust run
                    pass
                finally:
                    response.success()
            elif response.status_code == 400:
                response.success()
            else:
                print(f"DELETE PLANET: {response.text}")
                response.failure(response.text)

    @task
    def get_all_planets(self):
        """gets all Planets from the database"""
        with self.client.get("/api/planet", catch_response=True) as response:
            if response.status_code == 404 or response.status_code == 200:
                response.success()
            else:
                response.failure(response.text)

    @task
    def get_planet_by_name(self):
        """gets a random Planet from the database: uses a random Planet planetName from the TestPlanets planets list"""
        with self.client.get(
                f"/api/planet/{TestPlanets.get_random_planet().planetName}",
                catch_response=True, name="/api/planet/{name}") as response:
            if response.status_code == 200 or response.status_code == 404:
                response.success()
            else:
                response.failure(response.text)

    @task
    def get_planet_by_id(self):
        """gets a random Planet from the database: uses a random Planet planetId from the TestPlanets planets list"""
        with self.client.get(
                f"/api/planet/id/{TestPlanets.get_random_planet().planetId}",
                name="/api/planet/id/{id}", catch_response=True) as response:
            if response.status_code == 200 or response.status_code == 404:
                response.success()
            else:
                response.failure(response.text)

    @task
    def create_moon(self):
        """creates a new Moon and stores it in the TestMoons' moons list for future use"""
        with self.client.post("/api/moon", json=asdict(new_moon := TestMoons.generate_moon()),
                              catch_response=True) as response:
            if response.status_code == 201:
                TestMoons.moons.append(new_moon)
                response.success()
            elif response.status_code == 400:
                response.success()
            else:
                response.failure(response.text)

    @task
    def delete_moon(self):
        """deletes a Moon and removes it from the TestMoons' moons list"""
        moon: Moon = TestMoons.get_random_moon()
        with self.client.delete(f"/api/moon/{moon.moonName}", name="/api/moon/{name}",
                                catch_response=True) as response:
            if response.status_code == 200:
                try:
                    TestMoons.moons.remove(moon)
                except ValueError:
                    # this prevents an uncaught exception from causing issues with the locust run
                    pass
                finally:
                    response.success()
            elif response.status_code == 400:
                response.success()
            else:
                response.failure(response.text)

    @task
    def get_all_moons(self):
        """gets all Moons from the database"""
        with self.client.get("/api/moon", catch_response=True) as response:
            if response.status_code == 200 or response.status_code == 404:
                response.success()
            else:
                response.failure(response.text)

    @task
    def get_moon_by_name(self):
        """gets a random Moon from the database: uses a random Moon moonName from the TestMoons moons list"""
        with self.client.get(f"/api/moon/{TestMoons.get_random_moon().moonName}",
                             name="/api/moon/{name}", catch_response=True) as response:
            if response.status_code == 200 or response.status_code == 404:
                response.success()
            else:
                response.failure(response.text)

    @task
    def get_moon_by_id(self):
        """gets a random Moon from the database: uses a random Moon moonId from the TestMoons moons list"""
        with self.client.get(f"/api/moon/id/{TestMoons.get_random_moon().moonId}",
                             name="/api/moon/id/{id}", catch_response=True) as response:
            if response.status_code == 200 or response.status_code == 404:
                response.success()
            else:
                response.failure(response.text)
