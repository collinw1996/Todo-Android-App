package edu.towson.cosc431.collinwoodruff.todosapp;

import org.junit.Test;
import org.mockito.Mockito;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void PresenterShouldCallViewMethod() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);

        // act
        presenter.launchAddTodoActivity();

        // assert
        Mockito.verify(mockView).launchNewTodo();
    }

    @Test
    public void PresenterCallsModelAndViewOnDelete() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);
        Todo todo = new Todo();

        // act
        presenter.deleteTodo(todo);

        Mockito.verify(mockView).refresh();
        Mockito.verify(mockModel).removeTodo(todo);
    }

    @Test
    public void PresenterCallsModelAndViewOnEdit() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);
        Todo todo = new Todo();

        // act
        presenter.launchEditTodo(todo);

        Mockito.verify(mockView).launchEditTodo(todo);


    }
}