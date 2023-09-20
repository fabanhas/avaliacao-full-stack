package com.tokiomarine.transaction;

import com.tokiomarine.controller.TransactionController;
import com.tokiomarine.model.Transaction;
import com.tokiomarine.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        Calendar schedulingDate = Calendar.getInstance();

        Transaction transaction = new Transaction();
        transaction.setValue(BigDecimal.valueOf(100.50));
        transaction.setTransferDate(schedulingDate);
        transaction.setFromAccount("07651");
        transaction.setToAccount("98076");
        transactions.add(transaction);

        doReturn(transactions).when(transactionService).getAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    public void testSchedule() throws Exception {

        doNothing().when(transactionService).schedule(any(Transaction.class), eq("A"));

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"A\"," +
                                "\"fromAccount\":\"Conta Origem\"," +
                                "\"toAccount\":\"Conta Destino\"," +
                                "\"value\":100.0," +
                                "\"transferDate\":\"2023-09-19T00:00:00.000+00:00\"}"))
                .andExpect(status().isCreated());

        verify(transactionService, times(1)).schedule(any(Transaction.class), eq("A"));
    }
}
