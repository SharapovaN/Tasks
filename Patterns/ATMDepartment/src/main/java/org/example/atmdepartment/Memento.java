package org.example.atmdepartment;

class Memento {
    private final State state;

    Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }
}
