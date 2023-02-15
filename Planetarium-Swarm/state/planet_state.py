import random

from entities.planet import Planet
from state.user_state import TestUsers


class TestPlanets:
    planets: list[Planet] = [Planet(userId=1, planetName="getPlanet", planetId=1)]

    @staticmethod
    def get_random_planet():
        """Returns a planet from the TestPlanets planet list, or returns dummy data"""
        try:
            return TestPlanets.planets[random.randint(0, len(TestPlanets.planets) - 1)]
        except ValueError:
            return Planet(userId=-1, planetName="TreasurePlanet", planetId=-1)

    @staticmethod
    def generate_planet() -> Planet:
        """generates a Planet with a random username and password"""
        random_planet_name: str = ""
        for num in range(10):
            # if second random.randint is 1 the letter is lowercase, else it is upper case
            random_planet_name += chr(random.randint(97, 122)) if random.randint(0, 1) == 1 else chr(
                random.randint(97, 122) - 32)
        return Planet(
            userId=TestUsers.get_random_user().userId,
            planetName=random_planet_name,
            planetId=0
        )
