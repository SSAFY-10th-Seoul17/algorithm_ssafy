# 본인 폴더만 Clone 하기


<br><br>

### 1. 로컬에 저장소 폴더를 생성한다.

![Untitled (4)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/934095c5-888a-413b-b488-240539df6d2d)

<br><br>

### 2. 터미널에 아래 명령어를 *변경 후, 입력한다.

- command

```bash

git init 

git config core.sparseCheckout true

git remote add -f origin https://github.com/{$Your ID}/Algorithm.git

git sparse-checkout set {$Your Folder Name}

git pull origin main
```

- exmaple

```bash
## 현재 폴더 Git 초기화
git init

## sparse-checkout 모드 활성화
git config core.sparseCheckout true

## git remote 연결. 본인이 fork 한 레퍼지토리 주소를 입력한다.
git remote add -f origin https://github.com/olrlobt/Algorithm.git

## sparse-checkout 모드에서 자신의 폴더만 checkout 하도록 설정한다.
git sparse-checkout set LeeSeungheon

## main 브랜치를 pull한다. << 앞서 설정한 폴더만 pull된다.
git pull origin main
```
![Untitled (5)](https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/07f3afc7-7a5a-498e-b26b-8870262601ce)


<br><br>

### 3. 이제 IDE에 혼자 있을 수 있다.

<img width="382" alt="Untitled (6)" src="https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/2f25c4d4-be7a-4b7b-8e8a-4048da497afe">


<br><br>

### 4. Git Staged에도 내 폴더를 제외한 폴더의 삭제, 변경 내역이 반영되지 않는다.

<img width="599" alt="Untitled (7)" src="https://github.com/SSAFY-10th-Seoul17/algorithm_ssafy/assets/99643732/c3b40e4e-f7ad-4538-a0f2-436e5d527baa">

