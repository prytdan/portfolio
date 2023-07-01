package com.example.task2.javafolder.repo;

import javax.inject.Inject;

public class RepositoryImpl implements Repository {

    private int usersInput = 0;

    @Inject
    public RepositoryImpl(Repository repositoryInterface) {
    }

    @Override
    public int getUsersInput() {
        return usersInput;
    }

    public void setUsersInput(int usersInput) {
        this.usersInput = usersInput;
    }

    public void cleanUpSingleton() {
        usersInput = 0;
    }

}
