package songpider.nadree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import songpider.nadree.model.PhotoEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

//사용자가 TodoDTO를 통해 todo 아이템 생성, 수정, 삭제
public class PhotoDTO {
    private String photoId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pDate;
    private boolean done;
    private String photoName;
    private String path;


    public PhotoDTO(final PhotoEntity entity) {
        this.photoId = entity.getPhotoId();
        this.pDate = entity.getPDate();
        this.photoName = entity.getPhotoName();
        this.path=entity.getPath();
        this.done = entity.isDone();
    }

    public String getImageURL(){
        try{
            return URLEncoder.encode(path + "/" + photoId + "_" + photoName,"UTF-8" );
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try{
            return URLEncoder.encode(path + "/" + photoId + "_" + photoName,"UTF-8" );
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    //controller에서 DTO를 Entity로 변환하기 위한 toEntity함수
    public static PhotoEntity toEntity(final PhotoDTO dto) {
        return PhotoEntity.builder()
                .photoId(dto.getPhotoId())
                .pDate(dto.getPDate())
                .photoName(dto.getPhotoName())
                .path(dto.getPath())
                .done(dto.isDone())
                .build();
    }

}
