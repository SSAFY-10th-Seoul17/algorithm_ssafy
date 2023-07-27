# 코드 리뷰

---

[Labels](#Labels)
<br>

[코멘트](#코멘트)
<br>

[Approve](#Approve)
--- 


## Labels

### 1. 라벨 설정하기

- 라벨을 이용해 PR 탭에서 리뷰를 요청하거나, 질문을 올렸다는 것을 명시하자.
- 오프라인 리뷰만 필요한 경우, 태그 없이 그냥 PR 하면 된다.
- 온라인 진행자들은 필수로 `Review plz` 를 체크한다.

![Untitled (54)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/6de814e7-0b90-4d6d-9191-ec01cf701f55)

- 적용된 화면

![Untitled (55)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/2a637b14-1f27-49a2-8598-f218a346bb0a)



## 코멘트


### 2. 일반 코멘트 달기

- PR 내부 Conversation 에서 가능



![image](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/193069c1-6393-4322-a2e0-d4afc0b96e0e)

![Untitled (50)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/4cdc6fbe-9d5c-41d6-853a-b78ac775a7d4)
- 아래처럼 보이게 된다.
![Untitled (51)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/a49c8ce9-3b82-4aad-9594-1074a2821b27)


<br><br>

### 3. 코드 태그하기

- PR내부 Files changed 탭으로 이동
- 코드에 마우스를 올리면 `+` 버튼이 보이게 되는데,
- 이를 드래그 하여 코드를 선택하고 Comment를 남긴다.
- `Add single comment` 의 경우 단순 리뷰 ( 주로 사용)
- `Start a review` 의 경우 PR 전체를 리뷰하는 기능이며, 간단한 수정 요청을 할 수 있으며, 수정 후 다시 승인하지 않으면 Merge 할 수 없다.

![image](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/693a90c2-cb0e-4e89-b146-b08399e3b239)
![Untitled (52)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/65214054-a28a-433f-9106-dea101e19f4a)


<br>

- 보이게 되는 화면
    - 아래처럼 답변을 달 수 있고,
    - 의문점을 해결하면 `Resolve` 버튼을 눌러주자.
 
![Untitled (53)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/8f57db94-ab82-47d5-810c-10af2b45df1a)

<br><br>

## Approve
- **Approve는 리뷰어를 등록 해 주어야 한다 !! **


- 페어끼리 코드 리뷰를 마쳤으면, 승인을 진행해야 공용 레퍼지에 Merge 할 수 있다.
- 대면으로 코드 리뷰를 진행했다면, 위처럼 Comment를 남길 필요 없이 Approve만 한다.
- 본인의 PR은 꼭 본인이 Merge 한다.


### 4. 페어와 코드리뷰를 진행 완료한 경우,
해당 PR > Files Changed > Review changes > Approve에 체크하고,
Submit review 해준다.

- 2명 이상의 페어(리뷰어 설정)가 Approve 해 주어야, Merge가 가능하다.

### 5. Approve 되지 않아 경고가 뜬 화면
- 이 경우 Merge할 수 없다.
![Untitled (41)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/104af712-492f-4ffe-a4ce-0004bc6b4929)




### 6. Approve 방법

- 내용은 작성하지 않아도 무관하다.
- 
    - Comment : 앞서 설명한, 일반 코멘트
    - Approve : Merge 승인 (본인이 확인 했다는 표시) 
    - Request Changes : 수정을 요청해달라는 뜻으로, 수정이 완료될 때 까지 Merge가 불가능하다.
![Untitled (42)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/d5ca6cc0-9234-4cb8-b1a8-fecd1c7fd7a1)





