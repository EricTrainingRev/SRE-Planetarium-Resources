from dataclasses import dataclass


@dataclass(kw_only=True)
class Planet:
    planetId: int
    planetName: str
    userId: int
