from dataclasses import dataclass


@dataclass(kw_only=True)
class Moon:
    moonId: int
    moonName: str
    planetId: int
