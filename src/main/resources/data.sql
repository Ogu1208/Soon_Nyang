-- Cat 데이터 샘플 추가
INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('루루', '3살', 'FEMALE', 100, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgPW2VrodfqgvWKVlM3PGjhYPTMVbEeFurj-6YsW8DlA&s', 'ACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('톰', '2살', 'MALE', 150, 'https://flexible.img.hani.co.kr/flexible/normal/743/478/imgdb/original/2023/0208/8916758411258711.jpg', 'ACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('제리', '7개월', 'MALE', 80, 'https://cdn.ntoday.co.kr/news/photo/202007/73742_46548_1519.jpg', 'ACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('미미', '1살', 'FEMALE', 200, 'https://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0311/IE002955775_STD.jpg', 'INACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('순냥이', '5살', 'MALE', 120, 'https://img.seoul.co.kr//img/upload/2023/04/20/SSC_20230420134023.jpg', 'ACTIVE');

INSERT INTO member(email, password, nickname, created_at)
VALUES ('kdasunb6@gmail.com', 'snsn22@@', '순냥이 집사 호소인', '2024-01-01 12:00:00');

INSERT INTO member(email, password, nickname, created_at)
VALUES ('snsnjerry@gmail.com', 'snsn22@!', '루루는 해적선장', '2023-02-11 12:00:00');

INSERT INTO post(latitude, longitude, content, image, like_count, member_id, cat_id, created_at)
VALUES
    (36.772250443829144, 126.93182972875724 , '미랩 앞에서 만난 귀여운 루루!', 'https://www.fitpetmall.com/wp-content/uploads/2023/09/shutterstock_2205178589-1-1.png', 35 , 1, 1, '2024-04-08 12:00:00'),
    (36.772204877679, 126.93094487795577, '오늘도 톰 만남 ㅠㅠ 행복하다 무서운척하는데 귀여움!', 'https://image.edaily.co.kr/images/photo/files/NP/S/2024/01/PS24010200845.jpg', 60, 1, 2, '2024-04-08 13:00:00'),
    (36.771506107341416, 126.93025103940869, '루루 어디가냥..', 'https://www.fitpetmall.com/wp-content/uploads/2023/09/shutterstock_2205178589-1-1.png', 55, 1, 3, '2024-04-08 14:00:00'),
    (36.76961125425657, 126.93374739800916 , '미미 친구랑 잔다 ㅋㅋ!', 'https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202111/07/762ce144-d666-4790-8609-c78416910e88.jpg', 43, 1, 4, '2024-04-08 15:00:00'),
    (36.773900501611664, 126.93362329223933, '헉 순냥이가 와서 막 부빔 ㅠㅠ 간택해줘', 'https://cdn.newspost.kr/news/photo/202305/108154_110862_456.jpg', 71, 1, 5, '2024-04-08 16:00:00'),
    (36.77231682566663, 126.93368906422113, '오늘도 루루는 귀엽다..', 'https://i.pinimg.com/originals/e1/18/f8/e118f88f4229fb2fad1dc146c4d656c0.jpg', 68, 1, 1, '2024-04-09 07:00:00');
