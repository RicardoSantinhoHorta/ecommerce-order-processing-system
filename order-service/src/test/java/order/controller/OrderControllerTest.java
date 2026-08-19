package order.controller;

import order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;


    //TESTS ITEM QUANTITY

    @Test
    void badRequestWhenItemQuantityIsInvalid() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": "??"
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    void badRequestWhenItemQuantityIsNegative() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": -2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void okRequestWhenItemQuantityIsZero() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": 0
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void okRequestWhenItemQuantityIsPositive() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isOk());
    }


    //TESTS ITEM PRICE

    @Test
    void badRequestWhenItemPriceIsZero() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 0,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestWhenItemPriceIsNegative() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": -30,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void badRequestWhenItemPriceIsInvalid() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": "??",
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void okRequestWhenItemPriceIsPositive() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isOk());
    }


    //TETS ITEM NAME

    //for now, a simple string is "correct". When inventory service is done, I will change the logic
    //to search on inventory if the item even exists.

    @Test
    void badRequestWhenItemNameIsInvalid() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": teclado,
                                  "price": 30,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void okRequestWhenItemNameIsCorrect() throws Exception {
        mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "orderItems": [
                                {
                                  "productId": 1,
                                  "name": "Teclado",
                                  "price": 30,
                                  "quantity": 2
                                }
                              ]
                            }
                            """)
                )
                .andExpect(status().isOk());
    }
}