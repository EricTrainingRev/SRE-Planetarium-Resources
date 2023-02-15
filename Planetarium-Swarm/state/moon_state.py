import random

from entities.moon import Moon
from state.planet_state import TestPlanets


class TestMoons:
    moons: list[Moon] = [Moon(planetId=1, moonName="getMoon", moonId=1)]

    @staticmethod
    def get_random_moon():
        """Returns a moon from the TestMoons moon list, or returns dummy data"""
        try:
            return TestMoons.moons[random.randint(0, len(TestMoons.moons) - 1)]
        except ValueError:
            return Moon(planetId=-1, moonName="DeathStar", moonId=-1)

    @staticmethod
    def generate_moon() -> Moon:
        """generates a Moon with a random name"""
        random_moon_name: str = ""
        for num in range(10):
            # if second random.randint is 1 the letter is lowercase, else it is upper case
            random_moon_name += chr(random.randint(97, 122)) if random.randint(0, 1) == 1 else chr(
                random.randint(97, 122) - 32)
        return Moon(
            planetId=TestPlanets.get_random_planet().planetId,
            moonName=random_moon_name,
            moonId=0
        )
