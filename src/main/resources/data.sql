-- Cat 데이터 샘플 추가
INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active, tnrdate)
VALUES ('루루', '3살', 'MALE', 100, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgPW2VrodfqgvWKVlM3PGjhYPTMVbEeFurj-6YsW8DlA&s', 'ACTIVE', '2023-07-20');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active, tnrdate)
VALUES ('티티', '2살', 'MALE', 150, 'https://flexible.img.hani.co.kr/flexible/normal/743/478/imgdb/original/2023/0208/8916758411258711.jpg', 'ACTIVE', '2024-01-12');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('제리', '7개월', 'FEMALE', 80, 'https://cdn.ntoday.co.kr/news/photo/202007/73742_46548_1519.jpg', 'ACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active)
VALUES ('미미', '1살', 'FEMALE', 200, 'https://ojsfile.ohmynews.com/STD_IMG_FILE/2022/0311/IE002955775_STD.jpg', 'INACTIVE');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active, tnrdate)
VALUES ('순냥이', '5살', 'MALE', 120, 'https://velog.velcdn.com/images/ogu1208/post/56b3b548-64eb-4ce7-a3d9-e3881faec509/image.jpg', 'ACTIVE', '2023-07-20');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active, tnrdate)
VALUES ('도도', '3살', 'MALE', 95, 'https://velog.velcdn.com/images/ogu1208/post/57db2e38-bc53-4472-832a-7757e1404bfc/image.jpg', 'ACTIVE', '2023-07-20');

INSERT INTO cat (name, age, gender, follower_cnt, image_url, is_active, tnrdate)
VALUES ('티치', '3살', 'MALE', 95, 'https://velog.velcdn.com/images/ogu1208/post/08b6f823-1cff-47ba-9c26-8b29013e5175/image.jpg', 'ACTIVE', '2023-07-20');

INSERT INTO member(email, password, nickname, created_at)
VALUES ('kdasunb6@gmail.com', 'snsn22@@', '순냥이 집사 호소인', '2024-01-01 12:00:00');

INSERT INTO member(email, password, nickname, created_at)
VALUES ('snsnjerry@gmail.com', 'snsn22@!', '루루는 해적선장', '2023-02-11 12:00:00');

INSERT INTO post(latitude, longitude, content, image, like_count, member_id, cat_id, created_at)
VALUES
    (36.772250443829144, 126.93182972875724 , '미랩 앞에서 만난 귀여운 루루!', 'https://www.fitpetmall.com/wp-content/uploads/2023/09/shutterstock_2205178589-1-1.png', 35 , 1, 1, '2024-04-08 12:00:00'),
    (36.772204877679, 126.93094487795577, '오늘도 티티 만남 ㅠㅠ 행복하다 무서운척하는데 귀여움!', 'https://image.edaily.co.kr/images/photo/files/NP/S/2024/01/PS24010200845.jpg', 60, 1, 2, '2024-04-08 13:00:00'),
    (36.771506107341416, 126.93025103940869, '루루 어디가냥..', 'https://www.fitpetmall.com/wp-content/uploads/2023/09/shutterstock_2205178589-1-1.png', 55, 1, 3, '2024-04-08 14:00:00'),
    (36.76961125425657, 126.93374739800916 , '미미 친구랑 잔다 ㅋㅋ!', 'https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202111/07/762ce144-d666-4790-8609-c78416910e88.jpg', 43, 1, 4, '2024-04-08 15:00:00'),
    (36.77216953542685, 126.93216583484818 , '이 냐옹이님이 순냥이가 맞나용 귀여워..', 'https://velog.velcdn.com/images/ogu1208/post/2351ae5a-3026-453f-89f0-99e26817086d/image.jpg', 71, 1, 5, '2024-03-06 13:15:00'),
    (36.77188327417833, 126.93189165991784, '따끈따끈한 순냥이 보고가세여 식빵굽고있음..!', 'https://velog.velcdn.com/images/ogu1208/post/fe631abd-9c96-4006-b7e6-410f91c870bd/image.jpg', 71, 1, 5, '2024-03-14 09:20:00'),
    (36.77237012472377, 126.93232527491465, '추운 날에도 식빵 굽는 용맹한 순냥이', 'https://velog.velcdn.com/images/ogu1208/post/8f6d055e-fc3a-4c84-8489-28f94595cecd/image.jpg', 71, 1, 5, '2024-03-15 14:20:00'),
    (36.773900501611664, 126.93362329223933, '속보!! 미랩 앞 순냥이 출현!! <br>나 개강하고 첨보는데 어쩜 이리 커여워,,, <br>진짜 말랑말랑하고 귀엽다 ㅜㅜ', 'https://velog.velcdn.com/images/ogu1208/post/fe631abd-9c96-4006-b7e6-410f91c870bd/image.jpg', 71, 1, 5, '2024-03-18 09:20:00'),
    (36.772257229469965, 126.93187732768533, '순냥이 보고가 아까 봣는데 진심 너무귀엽네..', 'https://velog.velcdn.com/images/ogu1208/post/56b3b548-64eb-4ce7-a3d9-e3881faec509/image.jpg', 71, 1, 5, '2024-04-02 08:024:00'),
    (36.77200294367846, 126.93236760185185, '비맞기 싫댜옹', 'https://cdn.newspost.kr/news/photo/202305/108154_110862_456.jpg', 71, 1, 5, '2024-04-08 16:00:00'),
    (36.77187654464999, 126.9319420708473, '꾸벅냥 순냥', 'https://velog.velcdn.com/images/ogu1208/post/21d241e3-ae53-4081-84d8-2defbe208a48/image.jpg', 71, 1, 5, '2024-04-10 17:59:00'),
    (36.771831466395, 126.93190290684316, '햇살밑에서 낮잠자는 순냥', 'https://velog.velcdn.com/images/ogu1208/post/b0f31695-ebd7-4e0d-b05a-ad30a57125c3/image.jpg', 71, 1, 5, '2024-04-12 14:59:00'),
    (36.773900501611664, 126.93362329223933, '순냥이 나한테 야옹해줌', 'https://velog.velcdn.com/images/ogu1208/post/62ad35d7-863d-48c0-877c-5de59b6ffc19/image.jpg', 71, 1, 5, '2024-04-15 14:11:00'),
    (36.77159048820183, 126.93202633166887, '오늘치 순냥이', 'https://velog.velcdn.com/images/ogu1208/post/34c9b614-795d-4487-a8fa-9ed31ed0f371/image.jpg', 71, 1, 5, '2024-04-16 16:00:00'),
    (36.77231682566663, 126.93368906422113, '오늘도 루루는 귀엽다..', 'https://i.pinimg.com/originals/e1/18/f8/e118f88f4229fb2fad1dc146c4d656c0.jpg', 68, 1, 1, '2024-04-09 07:00:00'),
    (36.77157467384015, 126.93194793819491, '용맹한 루루  밥주새오', 'https://pbs.twimg.com/media/DtvS0L2VYAE9adv.jpg', 68, 1, 1, '2024-04-11 07:00:00'),
    (36.7723406404155, 126.93197806444999, '도도 시크하게 생겨가지곤 궁디팡팡 해달라고 빵디 내밈', 'https://velog.velcdn.com/images/ogu1208/post/2cbdc850-373a-4ad1-ab56-31190d4e883c/image.jpg', 101, 1, 6, '2024-04-09 07:00:00'),
    (36.7719167828796, 126.93139878135783, '도도 그루밍', 'https://velog.velcdn.com/images/ogu1208/post/176a46f9-3929-4076-8ca8-03c3a3e0be31/image.jpg', 30, 1, 6, '2024-04-11 10:00:00'),
    (36.76987703533901, 126.93366036302051, '티치 발견!!', 'https://velog.velcdn.com/images/ogu1208/post/08b6f823-1cff-47ba-9c26-8b29013e5175/image.jpg', 68, 1, 7, '2024-03-20 13:15:00'),
    (36.7698681177358, 126.93382838291238, '티치 그늘밑에서 잠 ㅋㅋㅋ', 'https://velog.velcdn.com/images/ogu1208/post/3615779c-ae5c-40ce-8865-12d8f6c4c919/image.jpg', 68, 1, 7, '2024-04-01 11:15:00'),
    (36.76988375778547, 126.93359595253581, '아니 티치 세상모르고 자네 ㅋㅋㅋㅋㅋ', 'https://velog.velcdn.com/images/ogu1208/post/a842dc8e-9046-4c52-9465-09aa6d3cea4e/image.jpg', 68, 1, 7, '2024-04-08 11:15:00'),
    (36.77231682566663, 126.93368906422113, '시험기간에 루루보고 힐링', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjWP0AFIUxwa1qyk55UN0GRrLb55rkMSTIITfq78wJJw&s', 70, 1, 1, '2024-04-17 10:00:00');
