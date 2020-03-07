package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.entity.Commentary;
import pl.mojeprojekty.shop_v2.entity.Product;
import pl.mojeprojekty.shop_v2.entity.User;
import pl.mojeprojekty.shop_v2.repositories.CommentaryRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentaryService {

    private final CommentaryRepository commentaryRepository;

    public Commentary findByUserAndProduct(User user, Product product){
      return   commentaryRepository.findByUserAndProduct(user,product);
    }

    public void createCommentary(User user, Product product, String text){
        Commentary commentary = new Commentary();
        commentary.setProduct(product);
        commentary.setUser(user);
        commentary.setText(text);
        commentaryRepository.save(commentary);
    }

    public Commentary getCommentaryForProduct(Product product){
        Commentary commentary = null;
//      TODO create this method  -- to nie działa -- :-(
//         commentary = commentaryRepository.findByProduct(product)
//                .orElseThrow(() -> new NoSuchElementException("CUSTOM LOG: This product has no commentary"));
        return commentary;
    }

}
