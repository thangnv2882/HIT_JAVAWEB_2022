package com.hitbtvn_b5.service;

import com.hitbtvn_b5.dto.URLDTO;
import com.hitbtvn_b5.model.URL;
import com.hitbtvn_b5.repository.URLRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Service
public class URLService {

    @Autowired
    private URLRepository urlRepository;

    public static Long URLIdPresent;

    public List<URL> findAllUrls() {
        return urlRepository.findAll();
    }

    public URL findByURLId(Long id) {
        return urlRepository.findByURLId(id);
    }

    public void save(URL url) {
        urlRepository.save(url);
    }

    public void delete(URL url) {
        urlRepository.delete(url);
    }

    /*
    Hàm createShortenURL: rút gọn từ url gốc
    => Nếu : + Chuỗi ngẫu nhiên trùng với chuỗi trong db => Random chuỗi khác...
             + Chuỗi ngẫu nhiên không trùng với chuỗi nào trong db => lưu vào db => lấy id của url đó
    */
    public void createShortenURL(URL url) {
        List<URL> urls = urlRepository.findAll();
//        Nếu url gốc đã rút gọn rồi => lấy id của url đó
        for (URL i : urls) {
            if (i.getOriginalURL().compareTo(url.getOriginalURL()) == 0) {
                URLIdPresent = i.getURLId();
                return;
            }
        }
        boolean duplicate = true;
        String randomStr = "";
        while (duplicate) {
            randomStr = RandomStringUtils.randomAlphanumeric(7);
            if (checkDuplicateShortenURL(urls, randomStr)) {
                duplicate = true;
            } else {
                duplicate = false;
            }
        }
        url.setShortenURL(randomStr);
        urlRepository.save(url);
        URLIdPresent = url.getURLId();
    }

    /*
    Hàm getPageFromShortURL: trả về url gốc từ url đã rút gọn
    Nếu: + URL tồn tại trong db => Trả về url gốc
         + URL không tồn tại trong db => Trả về trang not found
     */
    public RedirectView getPageFromShortURL(String str) {
        RedirectView URLShort = new RedirectView();

        List<URL> urls = urlRepository.findAll();
        for (URL i : urls) {
            if (i.getShortenURL().compareTo(str) == 0) {
                URLShort.setUrl(i.getOriginalURL());
                return URLShort;
            }
        }
        RedirectView notFound = new RedirectView();
        notFound.setUrl("notFound");
        return notFound;
    }

    /*
    Hàm checkNull kiểm tra dữ liệu dưới body
    Nếu: + OriginalURL hoặc ShortenURL rỗng => false
         + OriginalURL và ShortenURL không rỗng => true

    */
    public boolean checkNull(URLDTO urlDTO) {
        if (
                urlDTO.getOriginalURL().compareTo("") == 0
                        || urlDTO.getShortenURL().compareTo("") == 0
        ) {
            return false;
        }
        return true;
    }

    /*
    Hàm checkDuplicate: Kiểm tra chuỗi rút gọn đã tồn tại chưa?
    Nếu: + Tồn tại => true
         + Không tồn tại => false
    */
    public boolean checkDuplicateShortenURL(List<URL> urls, String str) {
        for (URL i : urls) {
            if (i.getShortenURL().compareTo(str) == 0) {
                return true;
            }
        }
        return false;
    }
}
