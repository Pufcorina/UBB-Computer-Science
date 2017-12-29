% 3.
% a. Merge two sorted lists with removing the double values.
% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% merge all sublists with removing the double values.
% [1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5, [1, 1, 11], 8] =>
% [1, 2, 3, 4, 6, 7, 9, 10, 11]

% a

% Model matematic:
% my_merge(l1...ln, r1...rm) =
% 	l1...ln, m = 0
% 	r1...rn, n = 0
% 	l1 + my_merge(l2...ln, r1...rm), l1 < r1
% 	r1 + my_merge(l1...ln, r2...rm), l1 > r1
% 	my_merge(l1...ln, r2...rm), l1 = r1

% my_merge(L:list, R:list, R:list)
% flow model: my_merge(i, i, o)
my_merge(L, [], L).
my_merge([], R, R).
my_merge([HL|TL], [HR|TR], [HL|R]) :- HL < HR,
    my_merge(TL, [HR|TR], R).
my_merge([HL|TL], [HR|TR], [HR|R]) :- HL > HR,
    my_merge([HL|TL], TR, R).
my_merge([HL|TL], [HR|TR], R) :- HL = HR,
    my_merge([HL|TL], TR, R).

% b

% Model matematic:
% heterList(l1...ln) = 
% 	[], n = 0
% 	merge(l1, heterList(l2...ln)), is_list(l1) = True
% 	heterList(l2...ln), otherwise

% heterList(L:list, R:list)
% flow model: heterList(i, o)

heterList([], []).
heterList([H|T], R) :- is_list(H), !,
    heterList(T, RH),
    my_merge(H, RH, R).
heterList([_|T], R) :-
    heterList(T, R).