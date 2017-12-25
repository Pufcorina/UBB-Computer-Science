% 6.
% a. Write a predicate to test if a list is a set.
% b. Write a predicate to remove the first three occurrences of an element in a list. 
% If the element occurs less than three times, all occurrences will be removed.

% a

% Model matematic:
% count_occurences(l1...ln, e) =
% 	0, n = 0
% 	1 + count_occurences(l2...ln, e), l1 = e
% 	count_occurences(l2...ln, e), l1 != e

% count_occurences(L:list, E:number, R:number)
% count_occurences(i, i, o)

count_occurences([], _, 0).
count_occurences([H|T], E, R1) :- H =:= E,
    count_occurences(T, E, R),
    R1 is R + 1.
count_occurences([H|T], E, R) :- H =\= E,
    count_occurences(T, E, R).

% Model matematic:
% test_set(l1...ln) =
% 	true, n = 0
% 	false, count_occurences(l1...ln, l1) != 1
% 	test_set(l2...ln), otherwise

% test_set(L:list)
% test_set(i)

test_set([]).
test_set([H|T]) :- 
    count_occurences([H|T], H, R),
    R =:= 1,
    test_set(T), !.

% b

% Model matematic:
% remove_k_occurences(l1...ln, e, k) =
% 	[], n = 0
% 	l1...ln, k = 0
% 	remove_k_occurences(l2...ln, e, k - 1), l1 = e
% 	l1 + remove_k_occurences(l2...ln, e, k), l1 != e

% remove_k_occurences(L:list, E:number, K:number, R:list)
% remove_k_occurences(i, i, i, o)

remove_k_occurences([], _, _, []) :- !.
remove_k_occurences(L, _, 0, L) :- !.
remove_k_occurences([H|T], E, K, R) :- H =:= E,
    K1 is K - 1,
    remove_k_occurences(T, E, K1, R).
remove_k_occurences([H|T], E, K, [H|R]) :- H =\= E,
     remove_k_occurences(T, E, K, R).

% remove_3_occurences(L:list, E:number, R:list)
% remove_3_occurences(i, i, o)

remove_3_occurences(L, E, R) :- remove_k_occurences(L, E, 3, R).