from dataclasses import dataclass


@dataclass(kw_only=True)
class User:
    userId: int
    userUsername: str
    userPassword: str
