package vn.jewel.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.jewel.shop.dto.CardDtoModel;
import vn.jewel.shop.dto.ResponseDataModel;
import vn.jewel.shop.dto.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @RequestMapping(value = "/cart")
    public String index() {
        return "cart/cart";
    }

    @PostMapping(value = "/addCart")
    public ResponseModel addCart(CardDtoModel cardDtoModel, HttpServletRequest request){
        boolean isExistItem = false;
        List<CardDtoModel> listCard = (List<CardDtoModel>) request.getSession().getAttribute("cart");
        int index = 0;
        for(CardDtoModel cardDtoModel1 : listCard){
            if(cardDtoModel.getProductId() == cardDtoModel.getProductId()){
                isExistItem = true;
                listCard.get(index).setAmount(cardDtoModel.amount);
            }
            index++;
        }
        if(!isExistItem){
            listCard.add(cardDtoModel);
        }
        request.getSession().setAttribute("cart",listCard);
        return new ResponseModel(0,"Thành công");
    }

    @PostMapping(value = "/updateCart")
    public ResponseModel updateCart(List<CardDtoModel> cardDtoModelList, HttpServletRequest request){
        request.getSession().setAttribute("cart",cardDtoModelList);
        return new ResponseModel(0,"Thành công");
    }

    @GetMapping(value = "/getCart")
    public List<CardDtoModel> getCart(HttpServletRequest request){
        List<CardDtoModel> result = new ArrayList<>();
        if(request.getSession().getAttribute("cart")!=null){
            result = (List<CardDtoModel>) request.getSession().getAttribute("cart");
        }

        return result;
    }
}
