import random

from entities.user import User


class TestUsers:
    users: list[User] = [User(userId=1, userUsername="login", userPassword="user")]

    @staticmethod
    def get_random_user():
        """returns a random user from the TestUsers users list"""
        return TestUsers.users[random.randint(0, len(TestUsers.users) - 1)]

    @staticmethod
    def generate_user() -> User:
        """generates a User with a random username and password"""
        random_username: str = ""
        for num in range(10):
            # if second random.randint is 1 the letter is lowercase, else it is upper case
            random_username += chr(random.randint(97, 122)) if random.randint(0, 1) == 1 else chr(
                random.randint(97, 122) - 32)
        random_password: str = ""
        for num in range(10):
            # if second random.randint is 1 the letter is lowercase, else it is upper case
            random_password += chr(random.randint(97, 122)) if random.randint(0, 1) == 1 else chr(
                random.randint(97, 122) - 32)
        return User(userId=0, userUsername=random_username, userPassword=random_password)
